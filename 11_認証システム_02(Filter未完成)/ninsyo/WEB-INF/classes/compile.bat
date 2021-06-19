set CP=C:\Tomcat\lib\servlet-api.jar
javac -encoding UTF-8 -cp %CP% model\User.java model\LoginLogic.java controller\Main.java controller\Kengen.java controller\Logout.java filter\LoginFilter.java