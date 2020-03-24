# android-animated-vector
Animations (translate, rotate, scale, pathData...) on vector in XML. Animations are applied to group paths or paths to fully animate different elements of the vector..

<animated-vector/> requires Api 21

[IN PROGRESS]

<b>Animated Vector using animator/ObjectAnimator and animated-vector in XML</b>

I really struggled to find the right documentation/guidelines to animate separate paths or group paths from a single vector in XML.
I had to work on a vector which had like 10 different elements to animate - each of them differently - I really didn't want to write lines and lines of java code for it, it didn't seem efficient. I spent hours digging the net for the right way.
It is so easy once you have the right information!


This repo is for you if:
- You're trying to animate a vector using XML and ObjectAnimator
- You're trying to animate several paths in one vector
- You're trying to use/customise simple translate or scale animations but everything you find online does not work!

You will find the following animations in this repo:
- translate 
- scale 
- rotate



/* TRANSLATE ANIMATION TUTORIAL */

1. Create your Android Project

2. Create your Vector Asset
   right-click on drawable > New > Vector Asset. 
   I uploaded a cute little robot as an SVG file, selected a name, clicked Next then Finish
   
   check out your vector.xml, you should see different paths representing each element drawn.

3. Create your animations folder
   right-click on res > New > Android Resource Directory, and add as below:
   Directory name: animator
   Resource type: animator
   Source set: main
   click Ok

4. Create your first animation 
   Here, we'll create translate.xml (translate will move your element(in the direction you want)
   right-click on animator > New > animator resource file)
   
   1st thing to know : if using animator, you'll have to use <objectAnimator>. Using <scale, or <translate, will not work.  Here's an example :
   
   ``` <objectAnimator
        android:duration="1500"
        android:interpolator="@android:anim/bounce_interpolator"
        android:propertyName="translateY"
        android:valueFrom="20"
        android:valueTo="0" />
   ```

        
    This objectAnimator represents one animation. If you want several animations together, easy, just surround the different <objectAnimators/> with <set></set>
    android:propertyName defines the action to be performed. Note here it is "translateY", and not "translationY". If you get this line wrong, nothing will happen.
   
    - translateY : up or down
    - translateX : left or right
    
    valueFrom and valueTo determine where to start the action and where to end it. Play with the values to find out which    direction you want!
    
    interpolator: adds some effect to your action. Here I'm adding a little bounce at the end.
    check out the other variations : https://thoughtbot.com/blog/android-interpolators-a-visual-guide
    
    Now, you have two choices:
    - animate the entire vector
    - animate a specific element of your vector
    
    I want to animate my little robot's right eye, and because I use a translate animation, I need to store that little eye into a group and give it a name.
    If you try to apply your translate.xml to the path instead of the group, you'll get the following error:
    
    java.lang.IllegalArgumentException: Property: translateY is not supported for FullPath
    
    in my vector_robot.xml:
    
 ``` 
 <group android:name="eyeGroup">

  <path
      android:pathData="M168.003,81.576a7.5,7.143 0,1 0,15 0a7.5,7.143 0,1 0,-15 0z"
      android:strokeAlpha="1"
      android:strokeLineJoin="round"
      android:strokeWidth="2"
      android:fillColor="#000000"
      android:strokeColor="#00000000"
      android:fillAlpha="1"
      android:strokeLineCap="round"/>

  </group>
```
    
  Done!
  And..if you want to animate the entire vector, just group all the paths into one "group" tag!
    
5. Create a new file in drawable > New > Drawable resource file, use the same name as your vector, add _animated at the end of it
   Here's the template:
   
  ``` 
   <?xml version="1.0" encoding="utf-8"?>
   <animated-vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/vector_robot">

    <target
        android:animation="@animator/translate"
        android:name="eyeGroup" />

   </animated-vector>
```

As you can see, I have added my target with the group I want to apply my translate to, and the translate itself.

6. Now, add your vector_animated.xml as an ImageView into your activity_main.xml, and add the following lines in your MainActivity.java:

```
        ImageView robot_imgview = findViewById(R.id.main_robot_imgview);

        Animatable animatable = (Animatable) robot_imgview.getDrawable();
        animatable.start();

```    
You're good to go!
Launch your app, watch your vector animate.
