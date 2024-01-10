<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Task Manager</title>
    <!-- Add your CSS styles or link a stylesheet here -->
    <style>
        .team-box {
            width: 200px;
            height: 200px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin: 20px;
            display: inline-block;
            text-align: center;
            line-height: 200px;
            font-size: 24px;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <div class="team-box" onclick="navigateToTeam('Team A')">Team A</div>
    <div class="team-box" onclick="navigateToTeam('Team B')">Team B</div>
    <div class="team-box" onclick="navigateToTeam('Team C')">Team C</div>
    <div class="team-box" onclick="navigateToTeam('Team D')">Team D</div>
    <div class="team-box" onclick="navigateToTeam('Team E')">Team E</div>

    <script>
        function navigateToTeam(teamName) {
            // Redirect to the tasks sheet relevant to the selected team
            window.location.href = '<%=request.getContextPath()%>/tasks?team=' + encodeURIComponent(teamName);
        }
    </script>

</body>
</html>
