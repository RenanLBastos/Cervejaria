package com.example.cervejaria.component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.enumeration.ReceitaEnum;
import com.example.cervejaria.request.ReceitaRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Date;

@Component
public class MontaReceitaComponent {

    private AmazonS3 s3client;

    private final File endPoint = new File(ReceitaEnum.ENDPOINT.getName());
    private final BufferedReader br = new BufferedReader(new FileReader(endPoint));

    private final File bucketName = new File(ReceitaEnum.BUCKETNAME.getName());
    private final BufferedReader brbucketName = new BufferedReader(new FileReader(bucketName));

    private final File accessKey = new File(ReceitaEnum.ACCESSKEY.getName());
    private final BufferedReader braccessKey = new BufferedReader(new FileReader(accessKey));

    private final File secretKey = new File(ReceitaEnum.SECRETKEY.getName());
    private final BufferedReader brsecretKey = new BufferedReader(new FileReader(secretKey));

    public MontaReceitaComponent() throws FileNotFoundException {
        // TODO document why this constructor is empty
    }

    @PostConstruct
    private void initializeAmazon() throws IOException {
        AWSCredentials credentials = new BasicAWSCredentials(this.braccessKey.readLine(), this.brsecretKey.readLine());
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

    private void uploadFileTos3bucket(String fileName, File file) throws IOException {
        s3client.putObject(new PutObjectRequest(brbucketName.readLine(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public Receita montaReceitaRequest(ReceitaRequest receitaRequest, MultipartFile multipartFile) throws IOException {
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
            String endpointUrl = br.readLine();
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
