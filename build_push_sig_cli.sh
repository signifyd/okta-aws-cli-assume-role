#!/usr/bin/env bash

# Builds a new okta-aws-cli-${build_version}-signifyd.jar and pushes it, along with its bash wrapper scripts
# and config, to the requested s3 bucket.

echo "Enter new build number:"

read build_version

echo "Enter s3 bucket to push to (enter to skip push):"

read s3_bucket

mvn -Dbuild.version=${build_version} package

jar_path=target
jar_name=okta-aws-cli-${build_version}-signifyd.jar

if [ ! -f ${jar_path}/${jar_name} ]; then
  echo "Couldn't find ${jar_path}/${jar_name}"
  exit 1
fi

tar_name=okta-aws-cli-${build_version}.tgz
tar zcvf $tar_name bin/* config.properties -C $jar_path $jar_name

echo "Created $tar_name"

if [ ! -z $s3_bucket ]; then
    aws s3 cp $tar_name s3://${s3_bucket} --acl public-read
fi
