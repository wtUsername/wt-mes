package com.wt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * <p>
 *     Api应用，采用 War形式启动，开发执行运行
 * </p>
 *
 * @author wt
 * @version 1.0
 * @since JDK 1.7
 */
@SpringBootApplication
@ServletComponentScan
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
