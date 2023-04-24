package org.koreait.cmmnCode.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CmmnCodeDto {

    // 공통 그룹 코드
    private String cmmnGroupCode;

    // 공통 코드
    private String cmmnCode;

    // 공통 코드명
    private String cmmnCodeNm;

    // 공통 코드 설명
    private String cmmnCodeDc;

    // 정렬 번호
    private long sort;

    // 사용 여부
    private String useAt;

    public static CmmnCode of(CmmnCodeDto cmmnCodeDto){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(cmmnCodeDto, CmmnCode.class);
    }
}
