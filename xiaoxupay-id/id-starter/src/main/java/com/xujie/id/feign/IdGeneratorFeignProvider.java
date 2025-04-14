package com.xujie.id.feign;

import com.xujie.common.entity.ResponseEntity;
import com.xujie.id.api.IdGeneratorFeignApi;
import jakarta.annotation.Resource;
import me.ahoo.cosid.IdGenerator;
import me.ahoo.cosid.provider.IdGeneratorProvider;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class IdGeneratorFeignProvider implements IdGeneratorFeignApi {
    @Resource
    private IdGeneratorProvider idGeneratorProvider;

    @Override
    public ResponseEntity<Long> getId(String name) {
        Optional<IdGenerator> idGenerator = idGeneratorProvider.get(name);
        boolean present = idGenerator.isPresent();
        if (!present) {
            return ResponseEntity.error("Id Generator 不存在");
        }
        return ResponseEntity.success(idGenerator.get().generate());
    }

}
