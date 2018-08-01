package codesquad.service;

import codesquad.domain.Promotion;
import codesquad.dto.PromotionDto;
import codesquad.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public List<PromotionDto> findAll() {
        return convertPromotionDto(promotionRepository.findAll());
    }

    private List<PromotionDto> convertPromotionDto(List<Promotion> promotions) {
        return promotions.stream().map((p) -> PromotionDto.fromEntity(p)).collect(Collectors.toList());
    }

    public Promotion save(PromotionDto promotionDto) {
        return promotionRepository.save(promotionDto.toEntity());
    }

    public void delete(Long id) {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("프로모션 데이터를 찾을 수 없습니다."));
        promotionRepository.delete(promotion);
    }
}
