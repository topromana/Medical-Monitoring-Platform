package application;

import application.repositories.MedicationPlanRepository;
import application.server.api.MedicationPLanProviderService;
import application.server.api.MedicationPlanProviderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration

@RequiredArgsConstructor
public class Server {

    private final MedicationPlanRepository medicationPlanRepo;

    @Bean(name = "/api/auth/medicationPlanProvider")
    HttpInvokerServiceExporter accountService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService( new MedicationPlanProviderServiceImpl(medicationPlanRepo) );
        exporter.setServiceInterface( MedicationPLanProviderService.class );
        return exporter;
    }

}
