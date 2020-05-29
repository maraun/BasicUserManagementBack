package kz.rbasicb.RBasicB;

import kz.rbasicb.RBasicB.shared.utils.beans.LuceneIndexConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
@EntityScan({"kz.rbasicb.RBasicB.models.entities.profile"})
@SpringBootApplication
@Import(LuceneIndexConfig.class)
public class RBasicBApplication {

	public static void main(String[] args) {
		SpringApplication.run(RBasicBApplication.class, args);
	}

}
