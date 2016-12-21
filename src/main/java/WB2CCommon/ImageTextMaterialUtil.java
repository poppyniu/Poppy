package WB2CCommon;

import com.domain.wx.MaterialImage;
import com.domain.wx.MaterialNews;
import com.util.MaterialImageUtil;
import com.util.MaterialNewsUtil;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImageTextMaterialUtil {

    @Test
    public MaterialNews createTextImageMaterial() {
        MaterialNews materialNews = new MaterialNews();
        materialNews.setTitle("test title");
        materialNews.setAuthor("test author");
        materialNews.setSummary("test summary");
        materialNews.setUrl("https://www.baidu.com");
        materialNews.setContent("test content");
        materialNews.setContentCover(false);
        materialNews.setDeleted(false);
        materialNews.setCompanyId(5);
        //set cover image
        MaterialImage materialImage = new MaterialImage();
        materialImage.setName("test.jpg");
        materialImage.setPath("/usr/scrm/wcpshare/5/test.jpg");
        materialImage.setCompanyId(5);
        materialImage.setCreatedBy("Poppy");
        materialImage.setCreatedDate(null);
        materialImage = MaterialImageUtil.createMaterialImage(materialImage);
        System.out.println("图片ID = "  +  materialImage.getId());
        materialNews.setImage(materialImage);
        materialNews = MaterialNewsUtil.createMaterial(materialNews);
        return materialNews;
    }

    @Test
    public void DeleteTextImageMaterial(long id) {
        MaterialNewsUtil.deleteMaterialNews(id);
    }

}
