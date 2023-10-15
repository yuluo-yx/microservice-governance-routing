package indi.yuluo.governance.istio.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuluo
 * @author 1481556636@qq.com
 */

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
@EnableFeignClients
public class IstioConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IstioConsumerApplication.class, args);
	}

	@FeignClient(name = "service-provider")
	public interface FeignService {

		/**
		 * Feign test.
		 * @return String
		 */
		@GetMapping("/test")
		String test();

	}

	@RestController
	public class Controller {

		@Autowired
		private IstioConsumerApplication.FeignService feignService;

		@GetMapping("/istio-label-routing")
		public String labelRouting() {
			return feignService.test();
		}

	}

}
