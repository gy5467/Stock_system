package org.koreait.cmmnCode.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

	// 공통 그룹 코드
	@Column(length = 45, nullable = false)
	private String cmmnGroupCode;

	// 공통 코드
	@Id
	@Column(length = 45, nullable = false)
	private String cmmnCode;

	// 공통 코드명
	@Column(length = 150, nullable = false)
	private String cmmnCodeNm;

	// 공통 코드설명
	@Column(length = 300)
	private String cmmnCodeDc;

	// 정렬일련번호
	private long sort;

	// 사용여부
	@Column(nullable = false, length = 1)
	private String useAt;

	// 최초 등록자 ID
	@Column(nullable = true, length = 12)
	private String regId;

	// 최초 등록일시
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime regDt;

	// 최종 등록자 ID
	@Column(nullable = true, length = 12)
	private String modId;

	// 최종 등록자 ID
	@Column(nullable = false)
	@UpdateTimestamp
	private LocalDateTime modDt;
}
