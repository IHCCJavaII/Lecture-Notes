package jobapp;

public class JobApplication {
    //Not Null and at least 2 characters
    private String fullName;
    //0-50
    private int yearsExperience;
    //Not Null
    private JobType jobType;
    private boolean availableImmediately;
    //Optional, but if provided must start with http:// or https://
    private String portfolioUrl;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) throws InvalidNameException {
        if (fullName == null || fullName.trim().length() < 2) {
            throw new InvalidNameException("Name must be at least 2 characters.");
        }
        this.fullName = fullName.trim();
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) throws InvalidExperienceException {
        if (yearsExperience < 0 || yearsExperience > 50) {
            throw new InvalidExperienceException("Years of experience must be between 0 and 50.");
        }
        this.yearsExperience = yearsExperience;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) throws InvalidJobTypeException {
        if (jobType == null) {
            throw new InvalidJobTypeException("Please select a job type.");
        }
        this.jobType = jobType;
    }

    public boolean isAvailableImmediately() {
        return availableImmediately;
    }

    public void setAvailableImmediately(boolean availableImmediately) {
        this.availableImmediately = availableImmediately;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) throws InvalidPortfolioException {
        if (portfolioUrl == null || portfolioUrl.isBlank()) {
            this.portfolioUrl = "";
            return;
        }

        String trimmed = portfolioUrl.trim();
        if (!trimmed.startsWith("http://") && !trimmed.startsWith("https://")) {
            throw new InvalidPortfolioException("Portfolio URL must start with http:// or https://");
        }
        this.portfolioUrl = trimmed;
    }

    @Override
    public String toString() {
        return "Name: " + fullName 
                + "\nExperience: " + yearsExperience + " years"
                + "\nJob Type: " + jobType
                + "\nAvailable Immediately: " + (availableImmediately ? "Yes" : "No")
                + "\nPortfolio URL: " + (portfolioUrl.isBlank() ? "N/A" : portfolioUrl);
    }
}
