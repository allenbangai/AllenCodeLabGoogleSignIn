package com.example.notekeeperapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jim.
 */

public final class CoursesInfo implements Parcelable {
    private CourseInfo mCourse;
    private String mTitle;
    private String mText;

    public CoursesInfo(CourseInfo course, String title, String text) {
        mCourse = course;
        mTitle = title;
        mText = text;
    }

    protected CoursesInfo(Parcel in) {
        mTitle = in.readString();
        mText = in.readString();
    }

    public static final Creator<CoursesInfo> CREATOR = new Creator<CoursesInfo>() {
        @Override

        public CoursesInfo createFromParcel(Parcel in) {
            return new CoursesInfo(in);
        }

        @Override
        public CoursesInfo[] newArray(int size) {
            return new CoursesInfo[size];
        }
    };

    public CourseInfo getCourse() {
        return mCourse;
    }

    public void setCourse(CourseInfo course) {
        mCourse = course;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mCourse.getCourseId() + "|" + mTitle + "|" + mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoursesInfo that = (CoursesInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mCourse, 0);
        dest.writeString(mTitle);
        dest.writeString(mText);
    }

}
