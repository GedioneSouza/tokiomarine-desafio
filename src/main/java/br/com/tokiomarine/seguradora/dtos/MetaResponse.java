package br.com.tokiomarine.seguradora.dtos;

public class MetaResponse {
    private int currentPage;
    private int itemsPerPage;
    private int totalOfItems;
    private int totalOfPages;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getTotalOfItems() {
        return totalOfItems;
    }

    public void setTotalOfItems(int totalOfItems) {
        this.totalOfItems = totalOfItems;
    }

    public int getTotalOfPages() {
        return totalOfPages;
    }

    public void setTotalOfPages(int totalOfPages) {
        this.totalOfPages = totalOfPages;
    }
}
