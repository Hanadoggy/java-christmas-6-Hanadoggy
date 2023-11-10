package christmas.service;

import christmas.entity.Beverage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class GiftPromotionServiceTest {
    GiftPromotionService service = new GiftPromotionService();
    OrderStatementStub receipt = new OrderStatementStub();

    @ValueSource(ints = {120000, 240000})
    @ParameterizedTest
    void 조건적용_성공_최소금액도달(int price) {
        receipt.setReturnPrice(price);

        assertThat(service.support(receipt)).isTrue();
    }

    @ValueSource(ints = {0,-10,1200, 11999})
    @ParameterizedTest
    void 조건적용_실패_최소금액미만(int price) {
        receipt.setReturnPrice(price);

        assertThat(service.support(receipt)).isFalse();
    }

    @Test
    void 할인적용_성공() {
        int result = Beverage.CHAMPAGNE.getPrice();

        assertThat(service.discount(receipt))
                .isEqualTo(result);
    }

}