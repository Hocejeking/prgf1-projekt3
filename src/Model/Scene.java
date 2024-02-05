package Model;

import transforms.Mat4;
import transforms.Mat4Identity;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final List<Solid> solids;
    private final List<Mat4> modelMats;

    public Scene() {
        solids=new ArrayList<>();
        modelMats=new ArrayList<>();
    }

    public  List<Mat4> getModelMats() {
        return modelMats;
    }


    public List<Solid> getSolids() {
        return solids;
    }

    public void addSolid(final Solid solid){
        solids.add(solid);
        modelMats.add(new Mat4Identity());
    }

    public void addSolid(final Solid solid,final  Mat4 modelMat){
        solids.add(solid);
        modelMats.add(new Mat4Identity());
    }
    public void removeSolid(final  Solid solid) {
        if (isInScene(solid)) {
            int index = solids.indexOf(solid);
            solids.remove(index);
            modelMats.remove(index);
        }
    }
    public boolean isInScene(final Solid solid) {
        return solids.contains(solid);
    }

}
