package cn.smiles.domain;

import java.util.List;

import lombok.Data;

@Data
public class Page<T> {
    public Page() {

    }

    public Page(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    private int page;
    private int pageSize;
    private int allCount;
    private List<T> list;

    public int getAllPage() {
        return getPageCount(allCount, pageSize);
    }
    
    public static int getPageCount(int allCount, int pageSize) {
        return allCount % pageSize == 0 ? allCount / pageSize : allCount / pageSize + 1;
    }
}
