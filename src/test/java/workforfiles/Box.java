package workforfiles;

import lombok.Getter;

@Getter
public class Box {
    public int id;
    public String description;
    public int number;
    public boolean active;
    public BoxSystemInfo systemInfo;
    public boolean onBoard;
}
