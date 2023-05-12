package org.koreait.cmmnCode.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CmmnCode {

	// 공통 코드 순번
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long cmmnCodeNo;

	// 공통 그룹 코드
	@Column(length = 45, nullable = false)
	private String cmmnGroupCode;

	// 공통 코드
	@Column(length = 45, nullable = false)
	private String cmmnCode;

	// 공통 코드명
	@Column(length = 150, nullable = false)
	private String cmmnCodeNm;

	// 공통 코드설명
	@Column(length = 300)
	private String cmmnCodeDc;

	// 정렬일련번호
	@Column(nullable = false)
	private String sort;

	// 사용여부
	@Column(nullable = false, length = 1)
	private String useAt;

	// 최초 등록자 ID
	@Column(nullable = true, length = 12)
	private String regId;

	// 최초 등록일시
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime regDt;

	// 최종 등록자 ID
	@Column(nullable = true, length = 12)
	private String modId;

	// 최종 등록자 ID
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDateTime modDt;
}
