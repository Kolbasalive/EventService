package org.kolbasa.event.service.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(
    packages = "org.kolbasa.event.service",
    importOptions = {ImportOption.DoNotIncludeTests.class}
)
public class ArchitectureTest {
    public static final String FW_LAYER = "FW";
    public static final String ADAPTER_LAYER = "ADAPTER";
    public static final String APP_API_LAYER = "API";
    public static final String APP_IMPL_LAYER = "IMPL";
    public static final String DOMAIN_LAYER = "DOMAIN";
    public static final String STAGING_LAYER = "STAGING";

    @ArchTest
    public void adapterLayerTest(JavaClasses classes) {
        getLayers()
            .whereLayer(ADAPTER_LAYER)
            .mayOnlyBeAccessedByLayers(FW_LAYER)
            .check(classes);
    }

    @ArchTest
    public void appApiLayerTest(JavaClasses classes) {
        getLayers()
            .whereLayer(APP_API_LAYER)
            .mayOnlyAccessLayers(DOMAIN_LAYER)
            .check(classes);
    }

    @ArchTest
    public void appImplLayerTest(JavaClasses classes) {
        getLayers()
            .whereLayer(APP_IMPL_LAYER)
            .mayOnlyBeAccessedByLayers(FW_LAYER)
            .check(classes);
    }

    @ArchTest
    public void domainLayerTest(JavaClasses classes) {
        getLayers()
            .whereLayer(DOMAIN_LAYER)
            .mayNotAccessAnyLayer()
            .check(classes);
    }

    @ArchTest
    public void fwTest(JavaClasses classes) {
        getLayers()
            .whereLayer(FW_LAYER)
            .mayNotBeAccessedByAnyLayer()
            .check(classes);
    }

/*    // включать только при наличии Staging
    @ArchTest
    public void stagingTest(JavaClasses classes) {
        getLayers()
        .whereLayer(STAGING_LAYER)
        .mayNotAccessAnyLayer();
    }*/

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private Architectures.LayeredArchitecture getLayers() {
        return Architectures.layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer(ADAPTER_LAYER).definedBy("..adapter..")
            .layer(APP_API_LAYER).definedBy("..app.api..")
            .layer(APP_IMPL_LAYER).definedBy("..app.impl..")
            .layer(DOMAIN_LAYER).definedBy("..domain..")
            .layer(FW_LAYER).definedBy("..fw..");
//            .layer(STAGING_LAYER).definedBy("..staging..");
    }
}