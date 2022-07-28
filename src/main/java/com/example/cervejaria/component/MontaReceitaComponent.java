package com.example.cervejaria.component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.request.ReceitaRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Component
public class MontaReceitaComponent {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3client = new AmazonS3Client(credentials);
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public Receita montaReceitaRequest(ReceitaRequest receitaRequest, MultipartFile multipartFile) {
        Receita novaReceita = new Receita();
        novaReceita.setNomeDaCerveja(receitaRequest.getNomeDaCerveja());
        novaReceita.setLitros(receitaRequest.getLitros());
        novaReceita.setAbv(receitaRequest.getAbv());
        novaReceita.setIbu(receitaRequest.getIbu());
        novaReceita.setOg(receitaRequest.getOg());
        novaReceita.setFg(receitaRequest.getFg());
        novaReceita.setCor(receitaRequest.getCor());
        novaReceita.setFamilia(receitaRequest.getFamilia());
        novaReceita.setIngredienteList(receitaRequest.getIngredientes());
        novaReceita.setMostura(receitaRequest.getMostura());
        novaReceita.setFervuraList(receitaRequest.getFervura());
        novaReceita.setFermentacaoMaturacaoList(receitaRequest.getFermentacaoMaturacao());
        novaReceita.setEnvase(receitaRequest.getEnvase());



        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = endpointUrl + "/" + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        novaReceita.setFoto(fileUrl);
        return novaReceita;
    }
}
