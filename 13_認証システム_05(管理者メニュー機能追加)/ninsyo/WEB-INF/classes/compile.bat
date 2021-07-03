cd C:\Tomcat\webapps\ninsyo\WEB-INF\classes
set CP=C:\Tomcat\lib\servlet-api.jar
javac -encoding UTF-8 -cp %CP% model\User.java controller\Main.java controller\Kengen.java controller\Logout.java dao\UserDAO.java controller\UserAdd.java controller\UserEdit.java controller\UserDel.java