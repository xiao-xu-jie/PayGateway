package com.xujie.domain.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.xujie.common.entity.ResponseEntity;
import com.xujie.common.enums.ResponseCodeEnum;
import com.xujie.common.exception.CustomException;
import com.xujie.domain.convert.SiteDomainConvert;
import com.xujie.domain.entity.Site;
import com.xujie.domain.service.SiteDomainService;
import com.xujie.id.api.IdGeneratorFeignApi;
import com.xujie.infra.entity.SiteInfo;
import com.xujie.infra.service.SiteService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import static com.xujie.id.constants.IdConstant.SITE_ID_SEQUENCE;

@Service
public class SiteDomainServiceImpl implements SiteDomainService {
    @Resource
    private SiteService siteService;
    @Resource
    private SiteDomainConvert siteDomainConvert;
    @Resource
    private IdGeneratorFeignApi idGeneratorFeignApi;

    @Override

    public void addOneSite(Site site) {
        ResponseEntity<Long> idResponse = idGeneratorFeignApi.getId(SITE_ID_SEQUENCE);
        if (ResponseCodeEnum.SUCCESS.getCode().equals(idResponse.getCode())) {
            Long id = idResponse.getData();
            // 生成ID、Secret
            site.setId(id);
            // appId 跟id相同
            site.setSiteAppid(String.valueOf(id));
            site.setSiteSecret(generateAppSec(id));
            siteService.insertOneSite(siteDomainConvert.bo2do(site));
        }

    }

    // 密钥初始化为appId 与 时间戳  跟随机字符串 的base64的md5编码
    private String generateAppSec(Long id) {
        String nonceStr = RandomUtil.randomString(6);
        long currentTime = System.currentTimeMillis();
        String str = currentTime + nonceStr + id;
        return DigestUtil.md5Hex16(str);
    }

    @Override
    public void deleteOneSite(Site site) {
        siteService.deleteOneSiteBySiteId(site.getSiteAppid());
    }

    @Override
    public Site getSiteByAppId(String appid) {
        SiteInfo siteInfo = siteService.selectOneByAppid(appid);
        if (ObjectUtils.isEmpty(siteInfo)) {
            throw new CustomException("站点不存在");
        }
        return siteDomainConvert.do2bo(siteInfo);
    }
}
