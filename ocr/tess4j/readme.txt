this module shows how you can read an image file and utilize the Tess4J library to read translate the image into readable text.

You need to get the trained data for your specific language here: https://github.com/tesseract-ocr/tessdata

If you are running linux, you might need to install tesseract package: pacman -S tesseract

To execute main:

java -jar build/libs/tess4j-1.0.0-SNAPSHOT.jar /home/seedollar/Downloads/Shivers.jpg

