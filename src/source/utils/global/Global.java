package source.utils.global;

import source.data.company.Company;
import source.data.job.Job;
import source.data.job.JobCategory;
import source.data.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Global {
    public static HashMap<String, User<?>> hashUsers = new HashMap<>();
    public static Scanner sc = new Scanner(System.in);
    public static List<Company> companies = new ArrayList<>();
    public static HashMap<Integer, JobCategory> jobCategories = new HashMap<>();
    public static List<Job> postedJobs = new ArrayList<>();

    static {
        initializeCompanies();
        initializeJobCategories();
    }

    private static void initializeCompanies() {
        companies.add(new Company("NVIDIA", "2788 San Tomas Expressway, Santa Clara, CA 95051", "+1 (408) 486-2000", "Technology"));
        companies.add(new Company("Microsoft", "One Microsoft Way, Redmond, WA 98052", "+1 (425) 882-8080", "Technology"));
        companies.add(new Company("Google", "1600 Amphitheatre Parkway, Mountain View, CA 94043", "+1 (650) 253-0000", "Technology"));
        companies.add(new Company("Amazon", "410 Terry Ave. North, Seattle, WA 98109", "+1 (206) 266-1000", "E-commerce"));
        companies.add(new Company("Apple", "One Apple Park Way, Cupertino, CA 95014", "+1 (408) 996-1010", "Technology"));
        companies.add(new Company("Meta", "1 Hacker Way, Menlo Park, CA 94025", "+1 (650) 308-7300", "Technology"));
        companies.add(new Company("Tesla", "13101 Tesla Road, Austin, TX 78725", "+1 (888) 518-3752", "Automotive/Technology"));
        companies.add(new Company("IBM", "1 New Orchard Road, Armonk, NY 10504", "+1 (914) 499-1900", "Technology"));
        companies.add(new Company("Intel", "2200 Mission College Blvd, Santa Clara, CA 95054", "+1 (408) 765-8080", "Technology"));
        companies.add(new Company("Oracle", "2300 Oracle Way, Austin, TX 78741", "+1 (737) 867-1000", "Technology"));
    }

    private static void initializeJobCategories() {
        jobCategories.put(1, new JobCategory("Software Engineer", "Designs, develops, and maintains software applications and systems."));
        jobCategories.put(2, new JobCategory("Marketing Specialist", "Develops and implements marketing strategies to promote products and services."));
        jobCategories.put(3, new JobCategory("Registered Nurse", "Provides direct patient care and assists in medical procedures."));
        jobCategories.put(4, new JobCategory("Accountant", "Prepares financial reports, manages budgets, and ensures compliance with tax laws."));
        jobCategories.put(5, new JobCategory("Web Designer", "Creates visually appealing and user-friendly websites."));
        jobCategories.put(6, new JobCategory("Mechanical Engineer", "Designs, develops, and oversees the manufacturing of mechanical systems."));
        jobCategories.put(7, new JobCategory("Human Resources Specialist", "Manages employee relations, recruitment, and benefits administration."));
        jobCategories.put(8, new JobCategory("Project Manager", "Coordinates and oversees the planning, execution, and completion of projects."));
        jobCategories.put(9, new JobCategory("Graphic Designer", "Creates visual concepts to communicate ideas through images, layouts, and typography."));
        jobCategories.put(10, new JobCategory("Electrician", "Installs, maintains, and repairs electrical systems and equipment."));
    }

    public static void header() {
        String[] headerLines = {
                "===============================================",
                "     __      _      _____ _           _        ",
                "    |  |    | |    |   __| |_ ___ ___| |___ ___",
                "    |  |__  | |__  |   __| '_|  _| . | | -_|  _|",
                "    |_____| |____| |__|  |_,_|_| |___|_|___|_|  ",
                "                                               ",
                "            Job Finder System                  ",
                "===============================================",
                "    Find Your Dream Career Path Today!        ",
                "==============================================="
        };

        for (String line : headerLines) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    public static Company findCompanyByName(String name) {
        return companies.stream()
                .filter(company -> company.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static List<Company> getCompaniesByIndustry(String industry) {
        return companies.stream()
                .filter(company -> company.getIndustry().equalsIgnoreCase(industry))
                .collect(Collectors.toList());
    }

    public static Company findCompanyById(String companyId) {
        return companies.stream()
                .filter(company -> company.getCompanyId().equalsIgnoreCase(companyId))
                .findFirst()
                .orElse(null);
    }
}
