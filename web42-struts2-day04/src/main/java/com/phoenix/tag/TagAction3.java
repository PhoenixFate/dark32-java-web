package com.phoenix.tag;

import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class TagAction3 extends ActionSupport {

    private String username;

    private String nickname;

    private String password;

    private String gender;

    private Integer sex;

    private List<Integer> hobby;

    private Integer edu;

    //private File photo;

    private String desc;

    @Override
    public String execute() throws Exception {
        System.out.println("tag action3");
        System.out.println("username: "+username);
        System.out.println("nickname: "+nickname);
        System.out.println("password: "+password);
        System.out.println("gender: "+gender);
        System.out.println("sex: "+sex);
        System.out.println("hobby: "+hobby);
        System.out.println("edu: "+edu);
        //System.out.println("photo: "+photo);
        System.out.println("desc: "+desc);

        this.addActionError("action error");

        return "success";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public List<Integer> getHobby() {
        return hobby;
    }

    public void setHobby(List<Integer> hobby) {
        this.hobby = hobby;
    }

    public Integer getEdu() {
        return edu;
    }

    public void setEdu(Integer edu) {
        this.edu = edu;
    }

//    public File getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(File photo) {
//        this.photo = photo;
//    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
