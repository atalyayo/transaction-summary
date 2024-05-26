package com.example.demo.common.helper;

import org.springframework.data.domain.PageRequest;

public class PagingHelper {

	public static PageRequest pageRequest(Integer page, Integer limit) {
		return PageRequest.of(page == null || page <= 0 ? 0 : (page - 1), limit == null ? 20 : limit);
	}

	private PagingHelper() {
		throw new IllegalStateException("Utility class");
	}
}
