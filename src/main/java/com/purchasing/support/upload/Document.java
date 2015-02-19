package com.purchasing.support.upload;

import br.com.caelum.vraptor.observer.upload.UploadedFile;
import com.purchasing.entity.Contract;
import com.purchasing.entity.RenewalContract;
import com.purchasing.support.date.Conversor;
import org.apache.commons.io.IOUtils;

import javax.enterprise.context.RequestScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author vanessa
 */

@RequestScoped
public class Document {

    private File documentFolder;

    public Document() {
           //catalina.home
        String caminhoPasta = System.getProperty("catalina.base") + "/webapps/contract";
        documentFolder = new File(caminhoPasta);
        if (!documentFolder.exists()) {
            documentFolder.mkdir();
        }
    }

    public String uploadContract(UploadedFile uploadedFile,Contract contract ) {
        File destinationFolder = new File(documentFolder, "");
        String fileName;
        try {
            IOUtils.copy(uploadedFile.getFile(), new FileOutputStream(destinationFolder));
            fileName = "contract" + contract.getSupplier().getId() + "_" + Conversor.converterDateInStringForDocument(contract.getInitialDate()) + ".pdf";
        } catch (IOException e) {
            throw new RuntimeException("Erro ao copiar imagem", e);
        }
        return fileName;
    }

    public File downloadContract(Contract contract) {
        File file = new File(documentFolder, "/contract" + contract.getSupplier().getId() + "_" + Conversor.converterDateInStringForDocument(contract.getInitialDate()) + ".pdf");
        return (file.exists()) ? file : new File(documentFolder, "/contract" + contract.getSupplier().getId() + "_" + Conversor.converterDateInStringForDocument(contract.getInitialDate()) + ".pdf");
    }

    public void deleteContract(Contract contract) {
        String path = documentFolder + "/contract" + contract.getSupplier().getId() + "_" + Conversor.converterDateInStringForDocument(contract.getInitialDate()) + ".pdf";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    public String uploadRenewalContract(UploadedFile uploadedFile, RenewalContract renewalContract ) {
        File destinationFolder = new File(documentFolder, "");
        String fileName;
        try {
            IOUtils.copy(uploadedFile.getFile(), new FileOutputStream(destinationFolder));
            fileName = "contract" + renewalContract.getContract().getSupplier().getId() + "_" + Conversor.converterDateInStringForDocument(renewalContract.getInitialDate()) + ".pdf";
        } catch (IOException e) {
            throw new RuntimeException("Erro ao copiar imagem", e);
        }
        return fileName;
    }

    public File downloadRenewalContract(RenewalContract renewalContract) {
        File file = new File(documentFolder, "/contract" + renewalContract.getContract().getSupplier().getId() + "_" + Conversor.converterDateInStringForDocument(renewalContract.getInitialDate()) + ".pdf");
        return (file.exists()) ? file : new File(documentFolder, "/contract" + renewalContract.getContract().getSupplier().getId() + "_" + Conversor.converterDateInStringForDocument(renewalContract.getInitialDate()) + ".pdf");
    }

    public void deleteRenewalContract(Contract contract) {
        String path = documentFolder + "/contract" + contract.getSupplier().getId() + "_" + Conversor.converterDateInStringForDocument(contract.getInitialDate()) + ".pdf";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}
