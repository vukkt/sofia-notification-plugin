package com.eurodyn.controller;

import com.eurodyn.dto.AssetDto;
import com.eurodyn.service.impl.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.*;
import java.util.List;

@RestController
public class AssetController {

    static final List<String> allowedTypes = Arrays.asList("image/jpeg", "image/jpg", "image/png");

    @Autowired
    private AssetServiceImpl assetService;

    @PostMapping(path = "asset",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Map<String,String>> createAssetImage2(@ModelAttribute AssetDto assetDto) throws IOException {

        if (assetDto.getImage().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error","empty file"));
        }

        if (!allowedTypes.contains(assetDto.getImage().getContentType())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error","jpg, jpeg, or png allowed"));
        }

       return assetService.createAssetImage(assetDto);
    }

//    @PostMapping("/test")
//    public ResponseEntity<Map<String,String>> createAssetImage(@RequestBody AssetDto assetDto) {
//        try {
//            String imageBase64 = "";
//            String imageType = "";
//
//            String id = assetDto.getAsset_id();
//
//            String image = assetDto.getImage_base64();
//            String[] imageParts = image.split(",");
//            if(imageParts.length == 2){
//                imageType = imageParts[0];
//                imageBase64 = imageParts[1];
//            } else {
//                imageType = "data:image/jpeg;base64";
//                imageBase64 = imageParts[0];
//            }
//
//            String minimizedImage = ImageUtils.minimizeImage(imageBase64, newImageWidth);
//
//            String imageId = assetService.saveAssetImage(id, imageType+","+minimizedImage);
//            assetService.saveAssetOriginalImage(imageId, imageType+","+imageBase64);
//
//            return ResponseEntity.ok(Collections.singletonMap("asset_image_id", imageId));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Collections.singletonMap("error",  e.getMessage()));
//        }
//    }

//    public static String minimizeImage(String base64Image, int newWidth) {
//        try {
//            // Decode the Base64 string to a byte array
//            byte[] imageBytes = Base64.getDecoder().decode(base64Image);
//
//            // Load the image from the byte array
//            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
//
//            // Calculate the new height to maintain aspect ratio
//            int originalWidth = originalImage.getWidth();
//            int originalHeight = originalImage.getHeight();
//            int newHeight = (int) ((double) newWidth / originalWidth * originalHeight);
//
//            // Resize the image
//            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
//            BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g2d = newImage.createGraphics();
//            g2d.drawImage(resizedImage, 0, 0, null);
//            g2d.dispose();
//
//            // Encode the resized image to Base64
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(newImage, "jpeg", baos);
//            byte[] resizedImageBytes = baos.toByteArray();
//            String minimizedBase64Image = Base64.getEncoder().encodeToString(resizedImageBytes);
//
//            return minimizedBase64Image;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
