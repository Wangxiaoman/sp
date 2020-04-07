package cn.smiles.constants;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Constants {
    public static final int COMMON_INVALID = 0;
    public static final int COMMON_VALID = 1;
    
    public static final int PAGE_FROM_SCAFFOLD_DERIVATIVE = 1;

    public static final String CHEMBL_RPC_NAME = "chembl";
    public static final String ADMET_RPC_NAME = "admet";
    public static final String OCSR_RPC_NAME = "ocsr";

    public static final String CHEMBL_URL_PREFIX =
            "https://www.ebi.ac.uk/chembl/compound_report_card/";

    public static final String REQUEST_USER = "requestUser";

    public static final int FILE_TYPE_PDF = 1;
    public static final int FILE_TYPE_PICTURE = 2;
    public static final String FILE_PDF_CONVERT_TYPE = ".png";
    public static final String FILE_PDF = "pdf";

    public static final String FAKE_TARGET = "FAKE";
    public static final String DEFAULT_TARGET = "Q16539";

    public static final int FILE_OCSR_STATUS_RUNNING = 1;
    public static final int FILE_OCSR_STATUS_FINISH = 2;
    public static final int FILE_OCSR_STATUS_FINISH_NO_MOLS = 3;
    public static final int FILE_OCSR_TIMEOUT_SECOND = 2* 60 * 60;
    
    public static final int COMMON_USER_ORC_NUM_PER_DAY = 3;
    public static final int COMMON_USER_VIP_NUM_PER_DAY = 10;
    public static final long COMMON_USER_OCR_FILE_MAX_SIZE = 5 * 1024 * 1024;//5M

    public static final int EXPORT_EXCEL_HEIGHT = 3000;
    public static final int EXPORT_EXCEL_WIDTH = 9000;

    public static final String OCSR_MOLECULE_FILE_PATH = "mol/";

    public static final int SMILES_MAX_LENGTH = 4000;

    public static final String DEFAULT_FAKE_TARGET = "FAKE";

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_PAGE_SIZE = 9;
    
    public static final int BATCH_DOCKING_MAX_NUM = 50;

    public static final Set<String> FILE_SET = new HashSet<>();
    static {
        FILE_SET.add(".pdf");
        FILE_SET.add(".png");
        FILE_SET.add(".jpg");
        FILE_SET.add(".jpeg");
    }

    public static enum UserAccountType {
        DEFAULT, PHONE, MAIL, ACCOUNT
    }

    public static enum UserRole {
        DEFAULT, COMMON, VIP
    }

    public static enum UserAccountState {
        INVALID, REGISTERED, VALID
    }

    public static enum TaskType {
        DEFAULT, SCAFFOLD_DERIVATIVE, MOLECULE_OPTIMIZATION, SCAFFOLD_HOPPING, BATCH_DOCKING, OCR
    }
    
    public static Map<Integer,String> MOLECULE_GENERATE_NAME = new HashMap<>();
    static {
        MOLECULE_GENERATE_NAME.put(TaskType.SCAFFOLD_DERIVATIVE.ordinal(), "骨架衍生");
        MOLECULE_GENERATE_NAME.put(TaskType.MOLECULE_OPTIMIZATION.ordinal(), "分子优化");
        MOLECULE_GENERATE_NAME.put(TaskType.SCAFFOLD_HOPPING.ordinal(), "骨架跃迁");
        MOLECULE_GENERATE_NAME.put(TaskType.BATCH_DOCKING.ordinal(), "分子对接");
        MOLECULE_GENERATE_NAME.put(TaskType.OCR.ordinal(), "结构式提取");
    }

    public static enum MoleculeOptimizationType {
        DEFAULT, QED, SA_SCORE, HERG
    }

    public static enum MoleculeTargetType {
        DEFAULT, TARGET, POSITIVE_SMILES, FAKE, PDB
    }

    public static enum MoleculeFileType {
        DEFAULT, SDF, MOL, SMI
    }
    
    public static enum OcsrFileState {
        INVALID, RUNNING, FINISH, FINISH_NO_SMILES
    }
    
    public static final int DOWNLOAD_SELECT_TYPE_NEGATIVE = -1;
    public static final int DOWNLOAD_SELECT_TYPE_ALL = 0;
    public static final int DOWNLOAD_SELECT_TYPE_POSITIVE = 1;
    
    
    public static enum DockingDownloadFileType {
        DEFAULT, PDB, SMILES
    }
}
