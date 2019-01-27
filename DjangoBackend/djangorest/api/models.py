# api/models.py

from django.db import models


class Event(models.Model):
    # eventID is auto-incrementing fields,

    eventName = models.CharField(max_length=255, blank=False)
    eventID = models.AutoField(primary_key=True, auto_created=True)
    hostID = models.CharField(max_length=255, blank=False, unique=True)
    hostName = models.CharField(max_length=255, blank=False)
    eventDescription = models.CharField(max_length=255, blank=False)
    eventDescription = models.CharField(max_length=255, blank=False)
    displayTime = models.DateTimeField()
    startTime = models.DateTimeField()
    endTime = models.DateTimeField()
    location = models.CharField(max_length=255, blank=False, unique=True)
    maxRadius = models.IntegerField()
    dateCreated = models.DateTimeField(auto_now_add=True)
    dateModified = models.DateTimeField(auto_now=True)
    image = models.ImageField(upload_to=productFile, max_length=254, blank=)

    def __str__(self):
        """Return a human readable representation of the model instance."""
        return "{}".format(self.name)


class UserEvents(models.Model):

    userID = models.CharField(max_length=255, blank=False, unique=False)
    eventID = models.CharField(max_length=255, auto_created=True)
