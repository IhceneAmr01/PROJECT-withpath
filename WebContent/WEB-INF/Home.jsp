<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Your Homepage</title>

    <!-- Add your CSS styles here -->
    <style>
        body {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            color: #fff;
            text-align: center;
            font-family: Arial, sans-serif;
        }

        .content {
            max-width: 600px;
            margin: auto;
            padding: 20px;
        }

        h1 {
            font-size: 3em;
            margin-bottom: 10px;
        }

        p {
            font-size: 1.5em;
            margin-bottom: 20px;
        }

        .buttons {
            display: flex;
            justify-content: center;
        }

        .button {
            margin: 0 10px;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 5px;
            font-size: 1.2em;
            cursor: pointer;
        }

        /* Add your styles for the image slideshow */
        #slideshow {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1;
        }

        img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            animation: fadeIn 3s ease-in-out infinite;
        }

        @keyframes fadeIn {
            0% {
                opacity: 0;
            }

            25% {
                opacity: 0.25;
            }

            50% {
                opacity: 0.5;
            }

            75% {
                opacity: 0.75;
            }

            100% {
                opacity: 1;
            }
        }
    </style>
</head>

<body>

    <!-- Slideshow container -->
    <div id="slideshow">
        <img src="WEB-INF/img/pic1.jpg" alt="Slide 1">
        <img src="WEB-INF/img/pic2.jpg" alt="Slide 2">
        <img src="WEB-INF/img/pic3.png" alt="Slide 3">
        <!-- Add more images as needed -->
    </div>

    <div class="content">
        <h1>Your Title</h1>
        <p>Your Subtitle</p>
        <div class="buttons">
            <button class="button" onclick="redirectTo('/PROJECT/register')">Register</button>
            <button class="button" onclick="redirectTo('/PROJECT/login')">Sign Up</button>
        </div>
    </div>

    <!-- Add your JavaScript for the image slideshow -->
    <script>
        // Image slideshow
        const slideshow = document.getElementById('slideshow');
        const images = ['"WEB-INF/img/pic1.jpg"', '"WEB-INF/img/pic2.jpg"', '"WEB-INF/img/pic3.png'];
        let currentImage = 0;

        function changeImage() {
            slideshow.innerHTML = `<img src="${images[currentImage]}" alt="Slide ${currentImage + 1}">`;
            currentImage = (currentImage + 1) % images.length;
            setTimeout(changeImage, 5000); // Change image every 5 seconds (adjust as needed)
        }

        changeImage();

        // Redirect function
        function redirectTo(url) {
            window.location.href = url;
        }
    </script>

</body>

</html>
