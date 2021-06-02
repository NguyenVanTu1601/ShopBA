package mainstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreManagerBaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreManagerBaApplication.class, args);
	}

}

/*
Lỗi nếu sửa giá bán/ nhập thành trống thì sẽ bị lỗi null poiter
*/