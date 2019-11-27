package cn.faceland.springbootmydbdbquery;

import cn.faceland.springbootmydbdbquery.webDev.DbExecute;
import cn.faceland.springbootmydbdbquery.webDev.DbQuery;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SpringbootMydbDbqueryApplicationTests {

    @Test
    void contextLoads() {
        for(int i = 1 ; i < 200 ; i ++){
            DbExecute.go("insert into tb_common_save(id,content)  values(" + i + ",'')");
        }
//        DbQuery db = new DbQuery("select * from tb_common_save order by id desc");
//        for(int i = 0 ; i < db.row() ; i ++){
//            System.out.println(db.get(i,"id"));
//        }
    }

}
