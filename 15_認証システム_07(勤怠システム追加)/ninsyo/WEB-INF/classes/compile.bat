cd C:\Tomcat\webapps\ninsyo\WEB-INF\classes
set CP=C:\Tomcat\lib\servlet-api.jar
set LOG=C:\Tomcat\webapps\ninsyo\WEB-INF\lib\log4j-api-2.14.1.jar
javac -encoding UTF-8 -cp %CP%;%LOG% model\User.java controller\Main.java controller\Kengen.java controller\Logout.java dao\UserDAO.java controller\UserAdd.java controller\UserEdit.java controller\UserDel.java model\Attendance.java dao\AttendanceDAO.java