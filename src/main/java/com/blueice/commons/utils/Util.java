package com.blueice.commons.utils;


/**
* @Description: TODO
* @author blueice
* @date 2018年9月28日 下午1:57:43
*
*/
public class Util
{
    public static boolean isEmpty(String target){
        return target==null?
                true:(target.trim().length()>0?false:true);
    }
}
