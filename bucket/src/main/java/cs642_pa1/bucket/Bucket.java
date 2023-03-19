package cs642_pa1.bucket;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.*;

public class Bucket {

    // AWS Services Instances
    private final S3Client s3Client;

    // Application Variables
    private final String bucketName;
    private final String imagesPath;

    public Bucket() {
        bucketName = "cs442unr";
        imagesPath = "./images/";

        s3Client = DependencyFactory.s3Client();

        upload_images(imagesPath);

        s3Client.close();
    }

    public static void main(String[] args) {
        new Bucket();
    }

    private void upload_images(String path) {

        File[] images = new File(path).listFiles();

        for (File image : images) {

            String image_path = imagesPath + image.getName();

            PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(image.getName()).contentType("image/png").contentType("image/jpeg").contentType("image/jpg").build();

            s3Client.putObject(request, RequestBody.fromFile(new File(image_path)));

            System.out.println("Uploaded Image: " + image.getName());
        }

    }

}
