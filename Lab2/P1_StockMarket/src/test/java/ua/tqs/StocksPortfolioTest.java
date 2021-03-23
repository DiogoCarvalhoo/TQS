import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
class StocksPortfolioTest {

    @Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortfolio portfolio;

	@Test
    void getTotalValue() {
        portfolio.addStock( new Stock("EDP", 4) );
        portfolio.addStock( new Stock("TAP", 7) );


        when(market.getPrice("EDP")).thenReturn(10.0);
        when(market.getPrice("TAP")).thenReturn(5.5);

        assertEquals(portfolio.getTotalValue(), 78.5);
        assertThat(portfolio.getTotalValue(), is(78.5));

        verify(market, times(4)).getPrice(anyString());
    }


}