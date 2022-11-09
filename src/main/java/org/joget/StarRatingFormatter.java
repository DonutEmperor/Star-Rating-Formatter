package org.joget;

import org.joget.apps.app.service.AppPluginUtil;
import org.joget.apps.app.service.AppUtil;
import org.joget.apps.datalist.model.DataList;
import org.joget.apps.datalist.model.DataListColumn;
import org.joget.apps.datalist.model.DataListColumnFormatDefault;
import org.joget.workflow.util.WorkflowUtil;
import javax.servlet.http.HttpServletRequest;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class StarRatingFormatter extends DataListColumnFormatDefault{
    
    private final static String MESSAGE_PATH = "message/form/StarRatingFormatter";

    @Override
    public String getName() {
        return "Star Rating Formatter";
    }

    @Override
    public String getVersion() {
        return "8.0.0";
    }

    @Override
    public String getDescription() {
        return AppPluginUtil.getMessage("org.joget.StarRatingFormatter.pluginDesc", getClassName(), MESSAGE_PATH);
    }

    @Override
    public String getLabel() {
        return AppPluginUtil.getMessage("org.joget.StarRatingFormatter.pluginLabel", getClassName(), MESSAGE_PATH);
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/form/starRatingFormatter.json", null, true, MESSAGE_PATH);
    }
    
    @Override
    public String format(DataList dataList, DataListColumn column, Object row, Object value) {
    
        
        int star = Integer.parseInt((String)value);
        
        String result = "";
        
        //calling defaults settings from the user/json file
        String defaultFullStarColor = getPropertyString("fullStarColor");
        String defaultEmptyStarColor = getPropertyString("emptyStarColor");
        String defaultStarSize = getPropertyString("starSize");
        String defaultNumberOfStars = getPropertyString("numberOfStars");
        
        int starCount = Integer.parseInt(defaultNumberOfStars);
        
        HttpServletRequest request = WorkflowUtil.getHttpServletRequest();
        
        String html = "";
        
        if (request != null && request.getAttribute(getClassName()) == null){
        html = ".far.fa-star:before , .fas.fa-star:before{" + "font-size:"  + defaultStarSize + "px" + ";" + "}" + ".far.fa-star:before{" + "color:" + defaultEmptyStarColor + ";" + "}" +
                ".fas.fa-star:before{" + "color:" + defaultFullStarColor + ";" + "}" ;
        
        html = "<style type=\'text/css\'>" + html + "</style>";
        
        result += html;
        }
        
        
        //variables for full and empty stars
        String fullStar = "<i class=\"fas fa-star\"></i>";
        String empStar = "<i class=\"far fa-star\"></i>";

        for (int i = 0; i < starCount ; i++){
            
            if (star > 0){
                html += fullStar;
                star--;
            }else{
                html += empStar;
            }  
        }
        
        result += html;
        
        return result;
    }
    
}
