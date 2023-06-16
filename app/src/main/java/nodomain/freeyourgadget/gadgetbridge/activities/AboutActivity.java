/*  Copyright (C) 2015-2020 abettenburg, Andreas Shimokawa, Carsten Pfeiffer,
    Daniele Gobbetti, Lem Dulfo

    This file is part of Gadgetbridge.

    Gadgetbridge is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Gadgetbridge is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */
package nodomain.freeyourgadget.gadgetbridge.activities;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

import nodomain.freeyourgadget.gadgetbridge.BuildConfig;

import nodomain.freeyourgadget.gadgetbridge.R;

public class AboutActivity extends AbstractGBActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView about_hash = findViewById(R.id.about_hash);
        String versionHASH = BuildConfig.GIT_HASH_SHORT;
        about_hash.setText(String.format(getString(R.string.about_hash), versionHASH));

        TextView link1 = findViewById(R.id.links1);
        link1.setMovementMethod(LinkMovementMethod.getInstance());

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.yerzhan, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.omar, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.maga, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT);
        imageSlider.startSliding(3000);
    }
}
