package tdc.edu.vn.exesum;

public class Song {
    private String tenBai;
    private int hinhAnh;
    private int fileMp3;
    private String lyrics;

    public Song(String tenBai, int hinhAnh, int fileMp3, String lyrics){
        this.fileMp3 = fileMp3;
        this.hinhAnh = hinhAnh;
        this.tenBai = tenBai;
        this.lyrics = lyrics;
    }

    public String getTenBai() {
        return tenBai;
    }
    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }
    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getFileMp3() {
        return fileMp3;
    }
    public void setFileMp3(int fileMp3) {
        this.fileMp3 = fileMp3;
    }

    public String getLyrics() {
        return lyrics;
    }
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
