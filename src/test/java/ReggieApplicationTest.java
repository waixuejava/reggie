import com.li.reggie.ReggieApplication;
import com.li.reggie.dto.DishCategoryDto;
import com.li.reggie.mapper.DishMapper;
import com.li.reggie.service.DishService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author LI
 * @create 2022/6/27 13:56
 */
@SpringBootTest(classes = ReggieApplication.class )

public class ReggieApplicationTest {
    @Autowired
    DishMapper dishMapper;

    @Autowired
    DishService dishService;
    @Test
    public void test(){


    }
}
