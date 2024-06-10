package com.wisform.service;

import com.wisform.dao.PersonRepository;
import com.wisform.dao.ProjectRepository;
import com.wisform.entity.MappingTable_Person;
import com.wisform.entity.MappingTable_Project;
import com.wisform.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReuseService {
    public static String key1;
    public static String itemValue1;
    public static boolean flag1;
    public static String key2;//项目匹配
    public static String itemValue2;
    public static boolean flag2;
    public static List<String> personlist= new ArrayList<>();//person类里没有的属性
    public static List<String> personlistValue= new ArrayList<>();
    public static List<String> personlist2= new ArrayList<>();//person类里有的属性，可能需要更新
    public static List<String> personlistValue2= new ArrayList<>();
    public static List<String> projectlist= new ArrayList<>();//project类里没有的属性
    public static List<String> projectlistValue= new ArrayList<>();
    public static List<String> projectlist2= new ArrayList<>();//project类里有的属性，可能需要更新
    public static List<String> projectlistValue2= new ArrayList<>();
    public static MappingTable_Person mappingTablePerson = new MappingTable_Person();
    public  static MappingTable_Project mappingTableProject = new MappingTable_Project();

    @Autowired
    PersonRepository personRepository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    DynamicAddService dynamicAddService;


    public void Initial(){
        key1 = null;
        itemValue1 = null;
        flag1 = false;
        key2 = null;
        itemValue2 = null;
        flag2 = false;
        if(personlist !=null){
            personlist.clear();
        }
        if(personlistValue!=null){
            personlistValue.clear();
        }
        if(projectlist!=null){
            projectlist.clear();
        }
        if(projectlistValue!=null){
            projectlistValue.clear();
        }
        if(personlist2!=null){
            personlist2.clear();
        }
        if(personlistValue2!=null){
            personlistValue2.clear();
        }
        if(projectlist2!=null){
            projectlist2.clear();
        }
        if(projectlistValue2!=null){
            projectlistValue2.clear();
        }
    }

    public void personKey(String key,String itemValue) {
        key1 = key;
        itemValue1 = itemValue;
        flag1= (personRepository.findByKey(itemValue1) != null);
        //System.out.print("key1:"+key1+"\n");
        //System.out.print("itemValue1:"+itemValue1+"\n");
        //System.out.print("flag1:"+flag1+"\n");
    }

    public void projectKey(String key,String itemValue) {
        key2 = key;
        itemValue2 = itemValue;//判断
        flag2= (projectRepository.findByKey(itemValue2)!= null);
        System.out.print("key2:"+key2+"\n");
        System.out.print("itemValue2:"+itemValue2+"\n");
        System.out.print("flag2:"+flag2+"\n");
    }

    public String personMatch(String key){
        if(flag1){
            String ans = personRepository.getItemByKey(itemValue1,key);
            System.out.print("Ans: "+ans+'\n');
            if(ans!=null){  //如果返回ans非空说明有这个属性，添加到对应列表
                personlist2.add(key);
<<<<<<< HEAD
                personlistValue2.add("");
=======
>>>>>>> FrontEnd
            }
            return ans;
        }else{
            personlist.add(key);
<<<<<<< HEAD
            personlistValue.add("");
=======
>>>>>>> FrontEnd
            return null;
        }

    }

    public String projectMatch(String key){
        System.out.print("get in projectMatch\n");
        System.out.print("key普通:"+key+"\n");
        System.out.print("flag2: "+flag2);
        if(flag2){
            String ans = projectRepository.getItemByKey(itemValue2,key);
            if(ans!=null){
                projectlist2.add(key);
<<<<<<< HEAD
                projectlistValue2.add("");
=======
>>>>>>> FrontEnd
            }
            System.out.print("ans: "+ans+"\n");
            return ans;
        }else{
<<<<<<< HEAD
            if(projectlist.contains(key)){
                return null;
            }
            projectlist.add(key);
            projectlistValue.add("");
=======
            projectlist.add(key);
            System.out.print("add success to projectList\n");
>>>>>>> FrontEnd
            return null;
        }
    }

    public void savePerCouple(String key,String itemValue){
        int index;
        if(personlist!=null){
            index = personlist.indexOf(key);
        }else{
            index=0;
        }
        // 输出结果
        if (index != -1) {
<<<<<<< HEAD
            if((personlistValue.size()>index) && (personlistValue.get(index)!=null)){
                personlistValue.set(index,itemValue);
            }else{
                personlistValue.add(index,itemValue);
            }
        } else {
            index = personlist2.indexOf(key);
            if(index !=-1){
                personlistValue2.set(index,itemValue);
=======
            personlistValue.add(index,itemValue);
        } else {
            index = personlist2.indexOf(key);
            if(index !=-1){
                personlistValue2.add(index,itemValue);
>>>>>>> FrontEnd
            }
        }
    }

    public void saveProCouple(String key,String itemValue){
        System.out.print("get in saveProCouple\n");
        int index;
<<<<<<< HEAD
        System.out.print(projectlist+"\n");
        System.out.print(projectlistValue+"\n");
=======
>>>>>>> FrontEnd
        if(projectlist!=null){
            index = projectlist.indexOf(key);
        }else{
            index=0;
        }
<<<<<<< HEAD
        System.out.print(index+"\n");
        // 输出结果
        if (index != -1) {
            projectlistValue.set(index,itemValue);

        } else {
            index = projectlist2.indexOf(key);
            if(index !=-1){
                projectlistValue2.set(index,itemValue);

=======
        // 输出结果
        if (index != -1) {

            projectlistValue.add(index,itemValue);
        } else {
            index = projectlist2.indexOf(key);
            if(index !=-1){
                projectlistValue2.add(index,itemValue);
>>>>>>> FrontEnd
            }
        }
    }

    public void commitAll() {
        System.out.print(personlist + "\n");
        System.out.print(projectlist + "\n");
        System.out.print(projectlistValue+"\n");
        if (itemValue1 != null) {//ren
            if (flag1) {//存在，更新/加属性
                //更新属性
                dynamicAddService.addPer(itemValue1, personlist2, personlistValue2);
                //添加属性
                dynamicAddService.addPer(itemValue1, personlist, personlistValue);


            } else {
                personRepository.createPerson(itemValue1);
                dynamicAddService.addPer(itemValue1, personlist, personlistValue);
            }

        }
        if (itemValue2 != null) {//项目
            if (flag2) {//存在，更新/加属性
                //更新属性
                dynamicAddService.addPro(itemValue2, projectlist2, projectlistValue2);
                //添加属性
                dynamicAddService.addPro(itemValue2, projectlist, projectlistValue);

            } else {
                projectRepository.createProject(itemValue2);
                dynamicAddService.addPro(itemValue2, projectlist, projectlistValue);
            }
        }
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> FrontEnd
