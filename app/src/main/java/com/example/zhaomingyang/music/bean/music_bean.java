package com.example.zhaomingyang.music.bean;

public class music_bean {
    /**     * 歌手     */    public String singer;
    /**     * 歌曲名     */    public String song;
    /**     * 歌曲的地址     */    public String path;
    /**     * 歌曲长度     */    public int duration;
    /**     * 歌曲的大小     */    public long size;
    public music_bean(){};
    public music_bean(String singer,String song,String path,int duration,long size){
        this.singer = singer;
        this.song = song;
        this.path = path;
        this.duration = duration;
        this.size= size;
    };
    public String getSinger() {
        return singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    public String getSong() {
        return song;
    }
    public void setSong(String song) {
        this.song = song;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public long getSize() {
        return size;
    }
    public void setSize(long size) {
        this.size = size;
    }
}
