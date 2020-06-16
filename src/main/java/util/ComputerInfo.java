/*
package com.fatrui.accbooks.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import sun.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

public class ComputerInfo {
	
	private static final int CPUTIME = 30;

    private static final int PERCENT = 100;
    
    private static final int FAULTLENGTH = 10;
    
    private static String linuxVersion = null;
    
    private final static Date cussStartTime=new Date();
    
    public static void main(String [] args){
		 try {
			System.out.println(getComputerName());
			System.out.println(getAppStartTimeStr("yyyyMMdd HHmmss"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
    //获取app启动时间
   public static Date getAppStartTime(){
	   return cussStartTime;
   }
   public static String getAppStartTimeStr(String format){
	   SimpleDateFormat sdf=new SimpleDateFormat(format);
	  String str = sdf.format(cussStartTime);
	  return str;
   }
   
    //计算机名
    public static String getComputerName(){
    	 Map map=System.getenv();
		return (String) map.get("COMPUTERNAME");
    }
   
	//本机ip
	public static String getComputerIp() throws UnknownHostException{
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	//磁盘信息
	public static String getDiskInfo(String filePath) throws IOException{  
		File file=new File(filePath);
		if (!file.exists()) {
			return "";
		}
		StringBuffer sb=new StringBuffer();
       	 long totalSpace=file.getTotalSpace();    
         long usableSpace=file.getUsableSpace();  
         if(totalSpace>0){        
             sb.append(Math.round(((double)totalSpace/ (1024*1024*1024))*100/100.0) + "GB,");  
             if(Math.round((((double)usableSpace/ (1024*1024*1024))*100)/100.0)<=1){  
                 sb.append(Math.round((((double)usableSpace/ (1024*1024))*100)/100.0) + "MB");  
             }else{  
                 sb.append(Math.round((((double)usableSpace/ (1024*1024*1024))*100)/100.0) + "GB");  
             }  
         }  
        return sb.toString();  
    }  
	*/
/**
	 *内存
	 * @return   总内存MB,可用内存MB
	 *//*

	 public static String getMemery() {  
		StringBuffer sb=new StringBuffer();
	    int mb=1024*1024;
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总内存
        long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / mb;
        sb.append(totalMemorySize+"MB,");
        // 空闲物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / mb;
        sb.append(freePhysicalMemorySize+"MB");
        return sb.toString();
	 }  
	 
	
	 
	 */
/**
	   * CPU使用率 Windows系统
	   * @return
	   * @author xpWang
	 * @throws Exception 
	   *//*

	
	    public static double getCpuRatioForWindows() {  
	        try {  
	            String procCmd = System.getenv("windir") + "//system32//wbem//wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";  
	            // 取进程信息  
	            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));  
	            Thread.sleep(CPUTIME);  
	            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));  
	            if (c0 != null && c1 != null) {  
	                long idletime = c1[0] - c0[0];  
	                long busytime = c1[1] - c0[1];  
	                return Double.valueOf(PERCENT * (busytime) / (busytime + idletime)).doubleValue();  
	            } else {  
	                return 0.0;  
	            }  
	        } catch (Exception ex) {  
	            ex.printStackTrace();  
	            return 0.0;  
	        }  
	    }  
	    */
/**
	     * 读取CPU信息. 
	     * @param proc 
	     * @return 
	     * @author GuoHuang 
	     *//*

	    private static long[] readCpu(final Process proc) {  
	        long[] retn = new long[2];  
	        try {  
	            proc.getOutputStream().close();  
	            InputStreamReader ir = new InputStreamReader(proc.getInputStream());  
	            LineNumberReader input = new LineNumberReader(ir);  
	            String line = input.readLine();  
	            if (line == null || line.length() < FAULTLENGTH) {  
	                return null;  
	            }  
	            int capidx = line.indexOf("Caption");  
	            int cmdidx = line.indexOf("CommandLine");  
	            int rocidx = line.indexOf("ReadOperationCount");  
	            int umtidx = line.indexOf("UserModeTime");  
	            int kmtidx = line.indexOf("KernelModeTime");  
	            int wocidx = line.indexOf("WriteOperationCount");  
	            long idletime = 0;  
	            long kneltime = 0;  
	            long usertime = 0;  
	            while ((line = input.readLine()) != null) {  
	                if (line.length() < wocidx) {  
	                    continue;  
	                }  
	                // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,  
	                // ThreadCount,UserModeTime,WriteOperation  
	                String caption = StringUtil.substring(line, capidx, cmdidx - 1).trim();  
	                String cmd = StringUtil.substring(line, cmdidx, kmtidx - 1).trim();  
	                if (cmd.indexOf("wmic.exe") >= 0) {  
	                    continue;  
	                }  
	                String s1 = StringUtil.substring(line, kmtidx, rocidx - 1).trim();  
	                String s2 = StringUtil.substring(line, umtidx, wocidx - 1).trim();  
	                if (caption.equals("System Idle Process") || caption.equals("System")) {  
	                    if (s1.length() > 0)  
	                        idletime += Long.valueOf(s1).longValue();  
	                    if (s2.length() > 0)  
	                        idletime += Long.valueOf(s2).longValue();  
	                    continue;  
	                }  
	                if (s1.length() > 0)  
	                    kneltime += Long.valueOf(s1).longValue();  
	                if (s2.length() > 0)  
	                    usertime += Long.valueOf(s2).longValue();  
	            }  
	            retn[0] = idletime;  
	            retn[1] = kneltime + usertime;  
	            return retn;  
	        } catch (Exception ex) {  
	            ex.printStackTrace();  
	        } finally {  
	            try {  
	                proc.getInputStream().close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return null;  
	    } 

        */
/**
         * cpu使用率  linux
         * Linuxϵͳ
         * @return	
         * @throws IOException 
         *//*

        public static double getCpuRateForLinux() throws IOException{
	        InputStream is = null;
	        InputStreamReader isr = null;
	        BufferedReader brStat = null;
	        StringTokenizer tokenStat = null;
	        try{
	            System.out.println("Get usage rate of CUP , linux version: "+linuxVersion);

	            Process process = Runtime.getRuntime().exec("top -b -n 1");
	            is = process.getInputStream();                    
	            isr = new InputStreamReader(is);
	            brStat = new BufferedReader(isr);
	            
	            if(linuxVersion.equals("2.4")){
	                brStat.readLine();
	                brStat.readLine();
	                brStat.readLine();
	                brStat.readLine();
	                
	                tokenStat = new StringTokenizer(brStat.readLine());
	                tokenStat.nextToken();
	                tokenStat.nextToken();
	                String user = tokenStat.nextToken();
	                tokenStat.nextToken();
	                String system = tokenStat.nextToken();
	                tokenStat.nextToken();
	                String nice = tokenStat.nextToken();
	                
	                System.out.println(user+" , "+system+" , "+nice);
	                
	                user = user.substring(0,user.indexOf("%"));
	                system = system.substring(0,system.indexOf("%"));
	                nice = nice.substring(0,nice.indexOf("%"));
	                
	                float userUsage = new Float(user).floatValue();
	                float systemUsage = new Float(system).floatValue();
	                float niceUsage = new Float(nice).floatValue();
	                
	                return (userUsage+systemUsage+niceUsage)/100;
	            }else{
	                brStat.readLine();
	                brStat.readLine();
	                    
	                tokenStat = new StringTokenizer(brStat.readLine());
	                tokenStat.nextToken();
	                tokenStat.nextToken();
	                tokenStat.nextToken();
	                tokenStat.nextToken();
	                tokenStat.nextToken();
	                tokenStat.nextToken();
	                tokenStat.nextToken();
	                String cpuUsage = tokenStat.nextToken();
	                    
	                
	                System.out.println("CPU idle : "+cpuUsage);
	                Float usage = new Float(cpuUsage.substring(0,cpuUsage.indexOf("%")));
	                
	                return (1-usage.floatValue()/100);
	            }

	             
	        } catch(IOException ioe){
	            System.out.println(ioe.getMessage());
	            freeResource(is, isr, brStat);
	            throw ioe;
	            
	        } finally{
	            freeResource(is, isr, brStat);
	        }

	    }
        public static void freeResource(InputStream is, InputStreamReader isr, BufferedReader br){
	        try{
	            if(is!=null)
	                is.close();
	            if(isr!=null)
	                isr.close();
	            if(br!=null)
	                br.close();
	        }catch(IOException ioe){
	            System.out.println(ioe.getMessage());
	        }
	    }
	 
     */
/*   public static String substring(String src, int start_idx, int end_idx){
            byte[] b = src.getBytes();
            String tgt = "";
            for(int i=start_idx; i<=end_idx; i++){
                tgt +=(char)b[i];
            }
            return tgt;
        }*//*

}
*/
