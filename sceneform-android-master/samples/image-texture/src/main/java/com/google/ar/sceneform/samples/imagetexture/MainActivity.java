package com.google.ar.sceneform.samples.imagetexture;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Sceneform;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.RenderableInstance;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements
        BaseArFragment.OnTapArPlaneListener {
//NI OTHERS PUNYA MAIN MALAS NAK TUKA NAMA
    private ArFragment arFragment;
    private Renderable model;
    private Texture texture;
    ViewRenderable arFurDetails,arFurDetails2,arFurDetails3 ;
    String furniture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addFragmentOnAttachListener((fragmentManager, fragment) -> {
            if (fragment.getId() == R.id.arFragment) {
                arFragment = (ArFragment) fragment;
                arFragment.setOnTapArPlaneListener(MainActivity.this);
                Toast.makeText(getApplicationContext(), "Accessed", Toast.LENGTH_SHORT).show();
            }
        });
        if (savedInstanceState == null) {
            if (Sceneform.isSupported(this)) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.arFragment, ArFragment.class, null)
                        .commit();
            }
        }

        Bundle extras = getIntent().getExtras();

        loadModel();
        loadTexture();
    }

    public void loadModel() {
        WeakReference<MainActivity> weakActivity = new WeakReference<>(this);
        ViewRenderable.builder()
                .setView(this, R.layout.ar_fur_details_description)
                .build().thenAccept(renderable -> arFurDetails = renderable);
        ViewRenderable.builder()
                .setView(this, R.layout.ar_fur_details_description2)
                .build().thenAccept(renderable -> arFurDetails2 = renderable);
        ViewRenderable.builder()
                .setView(this, R.layout.ar_fur_details_description)
                .build().thenAccept(renderable -> arFurDetails3 = renderable);
//            ModelRenderable.builder()
//                    .setSource(this, Uri.parse("models/chair/chair.glb"))
//                    .setIsFilamentGltf(true)
//                    .build()
//                    .thenAccept(renderable ->  char1renderable = renderable)
//                    .exceptionally(
//                            throwable -> {
//                                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show();
//                                return null;
//                            });
//            ModelRenderable.builder()
//                    .setSource(this, Uri.parse("models/chair/gothic_chair.glb"))
//                    .setIsFilamentGltf(true)
//                    .build()
//                    .thenAccept(renderable ->  char2renderable = renderable)
//                    .exceptionally(
//                            throwable -> {
//                                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show();
//                                return null;
//                            });
//            ModelRenderable.builder()
//                    .setSource(this, Uri.parse("models/chair/office_chair.glb"))
//                    .setIsFilamentGltf(true)
//                    .build()
//                    .thenAccept(renderable ->  char3renderable = renderable)
//                    .exceptionally(
//                            throwable -> {
//                                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show();
//                                return null;
//                            });
        ModelRenderable.builder()
                    .setSource(this, Uri.parse("models/laptop.glb"))
                    .setIsFilamentGltf(true)
                    .build()
                    .thenAccept(model -> {
                        MainActivity activity = weakActivity.get();
                        if (activity != null) {
                            activity.model = model;
                        }
                    })
                    .exceptionally(
                            throwable -> {
                                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show();
                                return null;
                            });

        //            ModelRenderable.builder()
//                    .setSource(this, Uri.parse("models/chair/chair.glb"))
//                    .setIsFilamentGltf(true)
//                    .build()
//                    .thenAccept(model -> {
//                        MainActivity activity = weakActivity.get();
//                        if (activity != null) {
//                            activity.model = model;
//                        }
//                    })
//                    .exceptionally(
//                            throwable -> {
//                                Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show();
//                                return null;
//                            });
    }

    public void loadTexture(){
        WeakReference<MainActivity> weakActivity = new WeakReference<>(this);
        Texture.builder()
                .setSampler(Texture.Sampler.builder()
                        .setMinFilter(Texture.Sampler.MinFilter.LINEAR_MIPMAP_LINEAR)
                        .setMagFilter(Texture.Sampler.MagFilter.LINEAR)
                        .setWrapMode(Texture.Sampler.WrapMode.REPEAT)
                        .build())
                .setSource(this, Uri.parse("textures/parquet.jpg"))
                .setUsage(Texture.Usage.COLOR_MAP)
                .build()
                .thenAccept(
                        texture -> {
                            MainActivity activity = weakActivity.get();
                            if (activity != null) {
                                activity.texture = texture;
                            }
                        } )
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unable to load texture", Toast.LENGTH_LONG).show();
                            return null;
                        });
    }

    @Override
    public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
        Toast.makeText(getApplicationContext(), "in ontapplane", Toast.LENGTH_SHORT).show();
        if (model == null || texture == null ) {
            Toast.makeText(this, "Not Found (null)", Toast.LENGTH_SHORT).show();
            return;
        }
        // Create the Anchor for model
        Anchor anchor = hitResult.createAnchor();
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());
        //textdisplay in AR
        Anchor anchorText = hitResult.createAnchor();
        AnchorNode anchorNodeText = new AnchorNode(anchorText);
        anchorNodeText.setParent(arFragment.getArSceneView().getScene());


            TransformableNode displayText = new TransformableNode(arFragment.getTransformationSystem());
            displayText.setParent(anchorNodeText);
            RenderableInstance modelInstance = displayText.setRenderable(this.model);
            modelInstance.getMaterial().setInt("baseColorIndex", 0);
            modelInstance.getMaterial().setTexture("baseColorMap", texture);
            displayText.select();
            title(anchorNode, displayText, "LAPTOP 1\n\n" +
                                                    "Only for Junior Employees\n\n" +
                                                    "TAKE IT?\n\nTAPE TO CONFIRM");
        tukatitle(anchorNode, displayText, "");
        tukatitle2(anchorNode, displayText, "");


        ////textdisplay2 in AR
        Anchor anchorText2 = hitResult.createAnchor();
        AnchorNode anchorNodeText2 = new AnchorNode(anchorText2);
        anchorNodeText2.setParent(arFragment.getArSceneView().getScene());
    }

    private void tukatitle2(AnchorNode anchorNode, TransformableNode displayText, String s) {
        TransformableNode detailsViewAR = new TransformableNode(arFragment.getTransformationSystem());
        detailsViewAR.setRenderable(arFurDetails3);
        detailsViewAR.setLocalPosition(new Vector3(0.5f, displayText.getLocalPosition().y + 0.5f, 0));
        detailsViewAR.setParent(anchorNode);
        detailsViewAR.select();

        TextView arFurDetailsTV = (TextView) arFurDetails3.getView();
        arFurDetailsTV.setText(s);
        arFurDetailsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "TUKAAA Selected", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void tukatitle(AnchorNode anchorNode, TransformableNode displayText, String hoi) {
        TransformableNode detailsViewAR = new TransformableNode(arFragment.getTransformationSystem());
        detailsViewAR.setRenderable(arFurDetails2);
        detailsViewAR.setLocalPosition(new Vector3(0.3f, displayText.getLocalPosition().y + 0.3f, 0));
        detailsViewAR.setParent(anchorNode);
        detailsViewAR.select();

        TextView arFurDetailsTV = (TextView) arFurDetails2.getView();
        arFurDetailsTV.setText(hoi);
        arFurDetailsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "TUKAAA Selected", Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void title3() {

        TransformableNode displayText2 = new TransformableNode(arFragment.getTransformationSystem());
        RenderableInstance modelInstance2 = displayText2.setRenderable(this.model);
        modelInstance2.getMaterial().setInt("baseColorIndex", 0);
        modelInstance2.getMaterial().setTexture("baseColorMap", texture);
        displayText2.select();

        TransformableNode detailsText2 = new TransformableNode(arFragment.getTransformationSystem());
        detailsText2.setRenderable(arFurDetails3);
        detailsText2.setLocalPosition(new Vector3(0.5f, displayText2.getLocalPosition().y + 0.5f, 0));
        detailsText2.select();

        TextView arFurDetailsTV = (TextView) arFurDetails3.getView();
        arFurDetailsTV.setText("YAY COMPLETE");
        arFurDetailsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Details Selected", Toast.LENGTH_SHORT).show();
                //anchorNodeText.setParent(null);

            }
        });
    }


    private void title2() {

        TransformableNode displayText2 = new TransformableNode(arFragment.getTransformationSystem());
        RenderableInstance modelInstance2 = displayText2.setRenderable(this.model);
        modelInstance2.getMaterial().setInt("baseColorIndex", 0);
        modelInstance2.getMaterial().setTexture("baseColorMap", texture);
        displayText2.select();

        TransformableNode detailsText2 = new TransformableNode(arFragment.getTransformationSystem());
        detailsText2.setRenderable(arFurDetails2);
        detailsText2.setLocalPosition(new Vector3(0.6f, displayText2.getLocalPosition().y + 0.6f, 0));
        detailsText2.select();

        TextView arFurDetailsTV = (TextView) arFurDetails2.getView();
        arFurDetailsTV.setText("Please Pick a Mouse");
        arFurDetailsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Details Selected", Toast.LENGTH_SHORT).show();
                //anchorNodeText.setParent(null);
                title3();
            }
        });
    }


    private void title(AnchorNode anchorNodeText, TransformableNode displayText, String furniture) {
        TransformableNode detailsViewAR = new TransformableNode(arFragment.getTransformationSystem());
        detailsViewAR.setRenderable(arFurDetails);
        detailsViewAR.setLocalPosition(new Vector3(0.4f, displayText.getLocalPosition().y + 0.4f, 0));
        detailsViewAR.setParent(anchorNodeText);
        detailsViewAR.select();

        TextView arFurDetailsTV = (TextView) arFurDetails.getView();
        arFurDetailsTV.setText(furniture);
        arFurDetailsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Details Selected", Toast.LENGTH_SHORT).show();
                //anchorNodeText.setParent(null);
                title2();
            }
        });
    }
}
