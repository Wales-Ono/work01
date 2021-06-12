set CP=C:\Tomcat\lib\servlet-api.jar
javac -encoding UTF-8 -cp %CP% model\Board.java model\GameLogic.java controller\TitleServlet.java controller\GameServlet.java