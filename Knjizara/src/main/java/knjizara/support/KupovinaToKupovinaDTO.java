package knjizara.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import knjizara.model.Kupovina;
import knjizara.web.dto.KupovinaDTO;

@Component
public class KupovinaToKupovinaDTO implements Converter<Kupovina, KupovinaDTO>{

	@Override
	public KupovinaDTO convert(Kupovina kupovina) {
		KupovinaDTO dto = new KupovinaDTO();
		
		dto.setId(kupovina.getId());
		
		return dto;
	}

}
