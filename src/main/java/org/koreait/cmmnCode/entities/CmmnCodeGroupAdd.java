package org.koreait.cmmnCode.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CmmnCodeGroupAdd {

    // 공통 그룹 코드
    @NotBlank(message = "필수 입력 항목입니다.")
    private String cmmnGroupCode;

    // 공통 그룹 코드명
    @NotBlank(message = "필수 입력 항목입니다.")
    private String cmmnGroupNm;

    // 공통 그룹 코드설명
    private String cmmnGroupDc;

    public static CmmnCodeGroup of(CmmnCodeGroupAdd cmmnCodeGroupAdd){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(cmmnCodeGroupAdd, CmmnCodeGroup.class);
    }
}
