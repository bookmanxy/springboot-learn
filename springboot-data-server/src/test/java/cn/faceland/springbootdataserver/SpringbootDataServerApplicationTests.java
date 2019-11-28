package cn.faceland.springbootdataserver;

import cn.faceland.springbootdataserver.common.webDev.DbQuery;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootDataServerApplicationTests {

    @Test
    void contextLoads() {
        DbQuery db = new DbQuery("select * from user");
        for(int i = 0 ; i < db.row() ; i++){
            System.out.println(db.get(1,"name"));
        }
    }

}
