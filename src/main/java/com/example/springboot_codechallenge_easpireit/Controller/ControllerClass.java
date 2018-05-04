package com.example.springboot_codechallenge_easpireit.Controller;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ControllerClass {
	
	@Autowired
	ResourceLoader loader;
    
	
    @RequestMapping("/connected")
	public String yesorno(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
	  boolean ft=false;
	  String b="";
	  List<String> origin1=new ArrayList<String>();
	  List<String> destination1=new ArrayList<String>();
		Resource rs=loader.getResource("classpath:static/city.txt");
		try {
			 BufferedReader br=new BufferedReader(new InputStreamReader(rs.getInputStream()));
				
				while( (b = br.readLine())!=null) {
					String[] c=b.split(",");
					if(c[0].equals(origin)&&c[1].equals(destination)||c[1].equals(origin)&&c[0].equals(destination)) {
						ft=true;
						break;
					}
					if(c[0].equals(origin)) {
						origin1.add(c[1]);
					}
                    if(c[1].equals(origin)) {
						origin1.add(c[0]);
					}	
                    if(c[0].equals(destination)) {
					destination1.add(c[1]);
					}
                    if(c[1].equals(destination)) {
					destination1.add(c[0]);
					}
                  }
				 for(String d:origin1) {
	            	 for(String f:destination1) {
	            		if(d.equals(f)) {
	            			ft=true;
	            		}
	            	}
	            }
				br.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ft==true) {
			return "yes";
		}
		else {
			return "no";
		}
    }  

}
