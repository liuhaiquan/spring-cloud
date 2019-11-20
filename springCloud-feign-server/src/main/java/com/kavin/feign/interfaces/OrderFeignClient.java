package com.kavin.feign.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kavin.core.data.DataObject;
import com.kavin.feign.fallback.OrderFallBack;


/**
 * 声明一个Feign Client,此处的值是需要调用的服务名称，也就是applicaiton.yml 文件中的spring.application.name的值
 * 如果order 这个服务有多个，会自动进行负载均衡，
 * 
 * fallback:调用的接口失败之后会调用OrderFallBack对应的方法。
 * （例如findOrder地址调用失败之后会调用OrderFallBack类中的findOrder方法，OrderFallBack必须继承OrderFeignClient接口）
 */
@FeignClient(value ="order",fallback=OrderFallBack.class) 
public interface OrderFeignClient {

	/**
	 *springCloud 的注解翻译器翻译了这四个注解给feign使用。
	 * @RequestMapping
	 * @RequestParam
	 * @RequestHeader
	 * @PathVariable
	 */
	@RequestMapping(value = "/{model}/{method}")  //这里是需要调用的服务的mapping映射地址。@PathVariable得设置value（坑）
													//@PathVariable注解把方法中的参数传递给mapping映射地址中的参数
													//@RequestBody 把方法的参数dataObject当作参数传递给调用的服务接口。
	public DataObject commonAjax(@PathVariable("model") String model,@PathVariable("method") String method,@RequestBody DataObject dataObject);
	
}
