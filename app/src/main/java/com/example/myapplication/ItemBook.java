package com.example.myapplication;

public class ItemBook {
    private  int imageBook ;
    private String nameBook ;
    private String nameAuthor ;
    private String type ;
    private String Description;
    private String dateShow  ;
    private  String Rating ;
    private String URLBook;


    public ItemBook(
            int imageBook,
            String nameBook,
            String nameAuthor,
            String dateShow,
            String type,
            String Description,
            String Rating,
            String urlBook
    ){
        this.imageBook = imageBook ;
        this.nameBook = nameBook ;
        this.nameAuthor = nameAuthor ;
        this.dateShow = dateShow ;
        this.type = type ;
        this.Description = Description ;
        this.Rating = Rating ;
        this.URLBook = urlBook ;

    }

    public int getImageBook() {
        return imageBook;
    }
    public String getRating() {
        return Rating;
    }

    public String getURLBook(){
        return URLBook;
    }


    public String getDateShow() {
        return dateShow;
    }
    public String getDescription() {
        return Description;
    }
    public String getNameAuthor() {
        return nameAuthor;
    }
    public String getNameBook() {
        return nameBook;
    }
    public String getType() {
        return type;
    }
}
